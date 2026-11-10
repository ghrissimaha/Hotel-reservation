package com.hotel.hotelproject.exception;
import jakarta.servlet.http.HttpServletRequest; import org.springframework.http.*; import org.springframework.web.bind.MethodArgumentNotValidException; import org.springframework.web.bind.annotation.*; import java.time.Instant; import java.util.stream.Collectors;
@RestControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<ErrorResponse> response(HttpStatus s,String m,HttpServletRequest r){ return ResponseEntity.status(s).body(new ErrorResponse(Instant.now(),s.value(),s.getReasonPhrase(),m,r.getRequestURI())); }
    @ExceptionHandler(ResourceNotFoundException.class) ResponseEntity<ErrorResponse> notFound(ResourceNotFoundException e,HttpServletRequest r){return response(HttpStatus.NOT_FOUND,e.getMessage(),r);}
    @ExceptionHandler(BusinessRuleException.class) ResponseEntity<ErrorResponse> rule(BusinessRuleException e,HttpServletRequest r){return response(HttpStatus.CONFLICT,e.getMessage(),r);}
    @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<ErrorResponse> invalid(MethodArgumentNotValidException e,HttpServletRequest r){String m=e.getBindingResult().getFieldErrors().stream().map(x->x.getField()+": "+x.getDefaultMessage()).collect(Collectors.joining(", "));return response(HttpStatus.BAD_REQUEST,m,r);}
    @ExceptionHandler(Exception.class) ResponseEntity<ErrorResponse> generic(Exception e,HttpServletRequest r){return response(HttpStatus.INTERNAL_SERVER_ERROR,"Unexpected server error",r);}
}
