package com.monaco.prestamos

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

    private static final long serialVersionUID = 1

    String username
    String password
    String firstName
    String lastName
    boolean enabled = true
    Date dateCreated
    Date lastUpdated
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

    Set<Role> getRole() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role.flatten().unique() as Set<Role>
    }

    static constraints = {
        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: true
        firstName nullable: false, blank: false
        lastName nullable: false, blank: false
    }

    static mapping = {
        password column: '`password`'
        version false
    }

    def beforeInsert() {
        dateCreated = new Date()
    }

    def beforeUpdate() {
        lastUpdated = new Date()
    }

    String toString() {
        firstName + ' ' + lastName
    }

}
