package com.monaco.prestamos

import grails.validation.ValidationException
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured
import com.monaco.prestamos.User
import com.monaco.prestamos.Role
import com.monaco.prestamos.UserRole

@Transactional
//@Secured('permitAll')
@Secured(['ROLE_ADMIN'])
class RegisterController {

    static allowedMethods = [register: 'POST']

    def index() { }

    def register() {
        if (!params.password.equals(params.repassword)) {
            flash.message = 'Password and Re-Password not match'
            redirect action: 'index'
            return
        } else {
            try {
                def firstName = params.fullname.split(' ')[0]
                def lastName = params.fullname.split(' ')[1]

                def user = User.findByUsername(params.username)
                if (user == null) user = new User(username: params.username, password: params.password, firstName: firstName, lastName: lastName).save()
                def role = Role.get(params.role.id)
                if (user && role) {
                    UserRole.create user, role

                    UserRole.withSession {
                        it.flush()
                        it.clear()
                    }

                    flash.message = 'You have registered successfully. Please login.'
                    redirect controller: 'login', action: 'auth'
                } else {
                    flash.message = 'Register failed'
                    render view: 'index'
                    return
                }
            } catch (ValidationException e) {
                flash.message = 'Register Failed'
                redirect action: 'index'
                return
            }
        }
    }

}
