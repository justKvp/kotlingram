package org.acme.basepojo

class ErrorMsg {
    var code: Int? = null;
    var message: String? = null;
    constructor(code: Int, message: String) {
        this.code = code;
        this.message = message;
    }
}