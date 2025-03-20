package com.example.visitor_service.services;

import com.example.visitor_service.entities.Visitor;
import com.example.visitor_service.utils.ApprovalStatus;

import java.util.List;

public interface VisitorService {
    Visitor registerVisitor(Visitor visitor);

    Visitor getVisitorById(Integer id);

    List<Visitor> getAllVisitors();

    void updateCheckOutTime(Integer Id);

    String giveStatus(Integer id);

    Visitor updateStatus(Integer id, ApprovalStatus approvalStatus);

    Visitor updateQr(Integer id, String  qr);

    boolean checkValidUser(String contact_info,String password);
    Visitor findUserByContactInfo(String contact_info);
}
