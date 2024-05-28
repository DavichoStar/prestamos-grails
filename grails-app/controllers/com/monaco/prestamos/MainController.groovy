package com.monaco.prestamos

import grails.plugin.springsecurity.annotation.Secured

class MainController {

    static allowedMethods = [save: 'POST', update: 'PUT', delete: 'DELETE']
    def springSecurityService

    @Secured("isAuthenticated()")
    def index() {
        if (springSecurityService.currentUser.authorities.size() == 0) {
            flash.error = 'No cuenta con acceso a esta plataforma'
            redirect(controller:'logout', action: 'logout')
            return
        }

        def roles = springSecurityService.currentUser.getAuthorities().collect { it.authority }

        boolean hasAdmin = roles.contains('ROLE_ADMIN')
        boolean hasManager = roles.contains('ROLE_MANAGER')

        if (hasAdmin) {
            redirect(action: 'admin', params: params.lang ? [lang: params.lang] : [:])
        } else if (hasManager) {
            redirect(action: 'manager', params: params.lang ? [lang: params.lang] : [:])
        } else {
            flash.error = 'No cuenta con acceso a esta plataforma'
            redirect(controller:'logout', action: 'logout')
        }
    }

    @Secured('ROLE_ADMIN')
    def admin() { }

    @Secured('ROLE_MANAGER')
    def manager() { }
}
