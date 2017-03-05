package com.monodeepdas112.projectoasis.Interfaces;

/**
 * Created by monodeep on 5/3/17.
 * This interface is the interface which consists of a group of common functions and some constants
 * which are required and are common to all the User Interfaces.
 */

public interface MyCommonUISetUp {
    final int EMAIL_INVALID =1;
    final int PASSWORD_INVALID=2;
    final int EMAIL_EMPTY =3;
    final int FORM_DETAILS_VALID=4;
    void setUpUI();
    int validateUIForms();
}