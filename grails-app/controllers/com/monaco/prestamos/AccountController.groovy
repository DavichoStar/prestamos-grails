package com.monaco.prestamos

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_MANAGER'])
class AccountController {

    def index() { }

    def opening() { }

    def openingSave() {
        def account = new Cliente(params)

        if (account.save()) {
            flash.message = "Cuenta creada exitosamente"
            redirect controller: 'main', action: "index"
        } else {
            flash.error = "Error al crear la cuenta"
            model: [account: account]
        }
    }
}
