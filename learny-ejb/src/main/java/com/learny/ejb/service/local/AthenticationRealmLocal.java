package com.learny.ejb.service.local;

import javax.ejb.Local;

import org.apache.shiro.realm.Realm;

@Local
public interface AthenticationRealmLocal extends Realm {

}
