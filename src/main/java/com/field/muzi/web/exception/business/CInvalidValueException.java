package com.field.muzi.web.exception.business;

import com.field.muzi.web.common.dto.ErrorCode;

import javax.servlet.http.HttpServletResponse;

public class CInvalidValueException extends CBusinessException {
    public CInvalidValueException(ErrorCode errorCode) {
            super(errorCode);
    }

    public static class CAlreadySignedupException extends CInvalidValueException {
        public CAlreadySignedupException() {
            super(ErrorCode.ALREADY_SIGNEDUP);
        }
    }

    public static class CLoginFailedException extends CInvalidValueException {
        public CLoginFailedException() {
            super(ErrorCode.LOGIN_FAIL);
        }
    }

    public static class CStudyInvalidUserException extends CInvalidValueException {
        public CStudyInvalidUserException() {
            super(ErrorCode.STUDY_INVALID_USER);
        }
    }

    public static class CResumeInvalidUserException extends CInvalidValueException {
        public CResumeInvalidUserException() {
            super(ErrorCode.RESUME_INVALID_USER);
        }
    }

    public static class CVisaInvalidUserException extends CInvalidValueException {
        public CVisaInvalidUserException() {
            super(ErrorCode.VISA_INVALID_USER);
        }
    }

    public static class CAlreadyVisaException extends CInvalidValueException {
        public CAlreadyVisaException() {
            super(ErrorCode.ALREADY_REQUEST_VISA);
        }
    }

    public static class CBoardInvalidUserException extends CInvalidValueException {
        public CBoardInvalidUserException() {
            super(ErrorCode.BOARD_INVALID_USER);
        }
    }
}
