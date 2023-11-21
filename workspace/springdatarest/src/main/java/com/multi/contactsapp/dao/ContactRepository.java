package com.multi.contactsapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.contactsapp.domain.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
