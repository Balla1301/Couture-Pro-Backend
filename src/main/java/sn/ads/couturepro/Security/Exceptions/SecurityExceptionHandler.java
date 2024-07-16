package sn.ads.couturepro.Security.Exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.security.SignatureException;
import java.util.Map;
@ControllerAdvice
public class SecurityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleSecurityException(Exception exception, HttpServletResponse response) throws IOException {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (exception instanceof InternalAuthenticationServiceException) {
            status = HttpStatus.UNAUTHORIZED;
            return handleUnauthorized("Adresse email ou mot de passe invalide.", status);
        } else if (exception instanceof AccountStatusException) {
            status = HttpStatus.FORBIDDEN;
            return handleForbidden("The account is locked", status);
        } else if (exception instanceof AccessDeniedException) {
            status = HttpStatus.FORBIDDEN;
            return handleForbidden("You are not authorized to access this resource", status);
        } else if (exception instanceof SignatureException) {
            status = HttpStatus.FORBIDDEN;
            return handleForbidden("Invalid JWT signature", status);
        } else if (exception instanceof ExpiredJwtException) {
            status = HttpStatus.FORBIDDEN;
            return handleForbidden("JWT token has expired", status);
        }else if (exception instanceof PreAuthenticatedCredentialsNotFoundException) {
            // Ajoutez un traitement spécifique pour PreAuthenticatedCredentialsNotFoundException
            status = HttpStatus.UNAUTHORIZED;
            return handleUnauthorized("Pre-authenticated credentials not found", status);
        } else {
            // Handle other exceptions as needed
            return handleInternalServerError("Unknown internal server error", status, exception);
        }
    }

    private ResponseEntity<Object> handleInternalServerError(String message, HttpStatus status, Exception exception) {
        return ResponseEntity.status(status)
                .body(createProblemDetail(status.value(), status.getReasonPhrase(), exception.getMessage()));
    }
    private ResponseEntity<Object> handleUnauthorized(String message, HttpStatus status) {
        return ResponseEntity.status(status)
                .body(createProblemDetail(status.value(), status.getReasonPhrase(), message));
    }

    private ResponseEntity<Object> handleForbidden(String message, HttpStatus status) {
        return ResponseEntity.status(status)
                .body(createProblemDetail(status.value(), status.getReasonPhrase(), message));
    }

    private ResponseEntity<Object> handleInternalServerError(String message, HttpStatus status) {
        return ResponseEntity.status(status)
                .body(createProblemDetail(status.value(), status.getReasonPhrase(), message));
    }

    private Object createProblemDetail(int status, String title, String detail) {
        return Map.of(
                "status", status,
                "title", title,
                "detail", detail
        );
    }
}
