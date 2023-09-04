/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repository.impl;

import com.ntt.pojo.BaiViet;
import com.ntt.pojo.HinhAnh;
import com.ntt.pojo.NguoiDung;

import com.ntt.repository.BaiVietRepository;
import com.ntt.repository.TaiKhoanRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
public class BaiVietRepositoryImpl implements BaiVietRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private TaiKhoanRepository taikhoan;

    @Override
    public List<BaiViet> getBaiViet() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From BaiViet");
        return q.getResultList();
    }

    @Override
    public List<BaiViet> getBaiViet2(String tenBaiViet) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<NguoiDung> query = builder.createQuery(NguoiDung.class);
        Root root = query.from(NguoiDung.class);
        query = query.select(root);

        if (!tenBaiViet.isEmpty()) {
            Predicate p = builder.equal(root.get("tenBaiViet").as(String.class), tenBaiViet.trim());
            query = query.where(p);
        }

        Query q = s.createQuery(query);
        return q.getResultList();
    }

    @Override
    public Object getBaiVietById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        org.hibernate.query.Query q = s.createQuery("FROM BaiViet WHERE id= :i");
        q.setParameter("i", id);
        return q.getSingleResult();
    }

    @Override
    public List<Object> getBaiVietByType(String loaiBViet) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        List<Predicate> predicates = new ArrayList<>();
        Root rPost = q.from(BaiViet.class);
        q.select(rPost);

        Predicate p = b.equal(rPost.get("loaiBaiViet"), Integer.parseInt(loaiBViet));
        predicates.add(p);
        q.where(predicates.toArray(Predicate[]::new));

        org.hibernate.query.Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public List<Object> getBaiViet2Type(int loaiBViet) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        List<Predicate> predicates = new ArrayList<>();
        Root rPost = q.from(BaiViet.class);
        q.select(rPost);

        Predicate p = b.equal(rPost.get("loaiBaiViet"), loaiBViet);
        predicates.add(p);
        q.where(predicates.toArray(Predicate[]::new));

        q.orderBy(b.desc(rPost.get("id")));
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addBaiViet(BaiViet baiviet) {
        Session s = this.factory.getObject().getCurrentSession();

        try {
            s.save(baiviet);
            HinhAnh hinhanh = new HinhAnh();
            hinhanh.setIdBaiViet(baiviet);
            hinhanh.setDuongDan(baiviet.getHinhAnh());
            s.save(hinhanh);
            if (baiviet.getHinhAnh1() != null) {
                HinhAnh hinhanh1 = new HinhAnh();
                hinhanh1.setIdBaiViet(baiviet);
                hinhanh1.setDuongDan(baiviet.getHinhAnh1());
                s.save(hinhanh);
            }
            if (baiviet.getHinhAnh2() != null) {
                HinhAnh hinhanh2 = new HinhAnh();
                hinhanh2.setIdBaiViet(baiviet);
                hinhanh2.setDuongDan(baiviet.getHinhAnh2());
                s.save(hinhanh);
            }

            return true;
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateBaiViet(BaiViet baiviet) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.update(baiviet);
            return true;
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteBaiViet(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Object p = this.getBaiVietById(id);
        try {
            s.delete(p);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public List<Object> getBaiVietByIdNgDung(NguoiDung idNgDung) {
        Session s = this.factory.getObject().getCurrentSession();
        org.hibernate.query.Query q = s.createQuery("FROM BaiViet WHERE idNguoiDung= :idNguoiDung");
        q.setParameter("idNguoiDung", idNgDung);
        return q.getResultList();
    }

    @Override
    public BaiViet addBaiVietAPI(BaiViet baiviet) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(baiviet);

        return baiviet;

    }

    
}
