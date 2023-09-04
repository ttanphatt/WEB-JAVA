/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repository.impl;

import com.ntt.pojo.Follow;
import com.ntt.pojo.LoaiTaiKhoan;
import com.ntt.pojo.NguoiDung;
import com.ntt.repository.TaiKhoanRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ThanhThuyen
 */
@Repository
@Transactional
public class TaiKhoanRepositoryImpl implements TaiKhoanRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addTaiKhoan(NguoiDung nguoidung) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            session.save(nguoidung);
            Follow fl=new Follow();
            fl.setIdKhachHang(nguoidung);
            session.save(fl);
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public List<NguoiDung> getTaiKhoan(String username) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<NguoiDung> query = builder.createQuery(NguoiDung.class);
        Root root = query.from(NguoiDung.class);
        query = query.select(root);

        if (!username.isEmpty()) {
            Predicate p = builder.equal(root.get("tenTaiKhoan").as(String.class), username.trim());
            query = query.where(p);
        }

        Query q = s.createQuery(query);
        return q.getResultList();
    }

    @Override
    public LoaiTaiKhoan getLoaiTaiKhoan(String tenLoaiTaiKhoan) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("From LoaiTaiKhoan Where tenLoaiTaiKhoan = :tenLoaiTaiKhoan");
        q.setParameter("tenLoaiTaiKhoan", tenLoaiTaiKhoan);
        return (LoaiTaiKhoan) q.getSingleResult();

    }

    @Override
    public NguoiDung getTaiKhoanbyTenTK(String tenTK) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM NguoiDung WHERE tenTaiKhoan=:tenTK");
        q.setParameter("tenTK", tenTK);

        return (NguoiDung) q.getSingleResult();
    }

    @Override
    public NguoiDung getTaiKhoanId(int id) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        org.hibernate.query.Query q = s.createQuery("FROM NguoiDung WHERE id= :i");
        q.setParameter("i", id);
        return (NguoiDung) q.getSingleResult();
    }

}
