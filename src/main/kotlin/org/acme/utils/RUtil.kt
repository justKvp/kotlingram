package org.acme.utils

import jakarta.ws.rs.core.Response
import org.acme.basepojo.ErrorMsg

class RUtil {
    companion object {
        fun success(obj: Any):Response {
            return Response.status(Response.Status.OK)
                    .entity(obj)
                    .build();
        }

        fun preconditionFailed(code: Int, msg: String):Response {
            return Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(ErrorMsg(code, msg))
                    .build();
        }

        fun expectationFailed(code: Int, msg: String):Response {
            return Response.status(Response.Status.EXPECTATION_FAILED)
                    .entity(ErrorMsg(code, msg))
                    .build();
        }
    }
}