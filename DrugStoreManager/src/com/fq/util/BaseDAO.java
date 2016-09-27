package com.fq.util;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;

public class BaseDAO<E>{
	
	private HibernateTemplate hibernateTemplate;

	public void add(E entity){
		hibernateTemplate.save(entity);
	}	
	public void saveOrUpdate(E entity){
		hibernateTemplate.saveOrUpdate(entity);
	}
	
	public E load(Serializable id,Class<E> className){
		return hibernateTemplate.get(className, id);
	}
	/**
	 * 无条件的分页
	 * @return
	 */
	public PageModel<E> split(String hql,String hql_count,int currPage,int pageSize){
		PageModel<E>  model=new PageModel<E>();
		model.setCurrPage(currPage);
		model.setPageSize(pageSize);
		model.setPerIndex(getPerIndex(currPage));
		int total=(int)getTotal(hql_count);
		int totalpage=getTotalPage(total, pageSize);
		model.setNextIndex(getNextIndex(currPage, totalpage));
		model.setTotal(total);
		model.setTotalPage(totalpage);
		model.setList(getList(hql, currPage, pageSize));
		return model;
	}
	
	//计算总数据条数
	private long getTotal(final String hql_count){
		//select count(*) from UserBean 
		return  (long) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query=session.createQuery(hql_count);
				return query.list().get(0);
			}
		});
	}
	//总页数
	private int getTotalPage(int total,int pageSize){
		int yushu=total%pageSize;
		if(yushu!=0){
			return total/pageSize+1;
		}else{
			return total/pageSize;
		}
	}
	//上一页
	private int getPerIndex(int currpage) {
		if(currpage==1){
			return -1;
		}else{
			return currpage-1;
		}
	}

	//下一页
	private int getNextIndex(int currpage,int totalPage) {
		if(currpage>=totalPage){
			return -1;
		}else{
			return currpage+1;
		}
	}
	//查询结果集的
	private List<E> getList(final String hql,final int currPage,final int pageSize){
		//匿名内部类
		return (List<E>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				//10   0    10    20  
				int firstResult=(currPage-1)*pageSize;
				Query query=session.createQuery(hql);
				query.setFirstResult(firstResult);
				query.setMaxResults(pageSize);
				return query.list();
			}
		});
	}
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Autowired
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
}
