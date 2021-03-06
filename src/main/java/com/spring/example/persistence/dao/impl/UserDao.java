package com.spring.example.persistence.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Repository;

import com.spring.example.persistence.dao.IUserDao;
import com.spring.example.persistence.dao.common.AbstractHibernateDao;
import com.spring.example.persistence.model.User;

@Repository
@SuppressWarnings("unchecked")
public class UserDao extends AbstractHibernateDao<User> implements IUserDao {

	public UserDao() {
        super();
        setClazz(User.class);
    }

	@Override
	public List<User> listPaginatedUsers(int startIndex,int numberOfRecordsToFetch) {
		Query query = getCurrentSession().createQuery("SELECT user.id AS id,user.firstName AS firstName,user.lastName AS lastName,user.email AS email,user.role AS role FROM " +  getClazz().getName() + " user WHERE enabled = :enabled");
		query.setParameter("enabled", true);
		query.setFirstResult(startIndex);
		query.setMaxResults(numberOfRecordsToFetch);
		query.setResultTransformer(new AliasToBeanResultTransformer(getClazz()));
		return query.list();
		/*Criteria  criteria  = getCurrentSession().createCriteria(User.class);
	    
	    criteria.setProjection(Projections.projectionList()
	    								  .add(Projections.property("id"),"id")
	    								  .add(Projections.property("firstName"),"firstName")
	    								  .add(Projections.property("lastName"),"lastName")
	    								  .add(Projections.property("email"),"email")
	    								  .add(Projections.property("role"),"role"))
	    								  .setResultTransformer(Transformers.aliasToBean(User.class)); 
	    criteria.addOrder(Order.desc("id"));
	    return criteria.list();*/
	}
	
	@Override
	public List<User> getUserById(String id) {
		Query query = getCurrentSession().createQuery("FROM " +  getClazz().getName() + " WHERE email = :email");
		query.setParameter("email", id);
		return query.list();
	}
	
}