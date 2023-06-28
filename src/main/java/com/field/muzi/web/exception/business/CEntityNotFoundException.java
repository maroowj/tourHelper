package com.field.muzi.web.exception.business;


import com.field.muzi.web.common.dto.ErrorCode;

public class CEntityNotFoundException extends CBusinessException {
    public CEntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public static class CUserNotFoundException extends CEntityNotFoundException {
        public CUserNotFoundException() {
            super(ErrorCode.USER_NOT_FOUND);
        }
    }

    public static class CDiyNotFoundException extends CEntityNotFoundException {
        public CDiyNotFoundException() {
            super(ErrorCode.DIY_NOT_FOUND);
        }
    }

    public static class CDiyDetailsNotFoundException extends CEntityNotFoundException {
        public CDiyDetailsNotFoundException() {
            super(ErrorCode.DIY_DETAILS_NOT_FOUND);
        }
    }

    public static class CDiyOptionsNotFoundException extends CEntityNotFoundException {
        public CDiyOptionsNotFoundException() {
            super(ErrorCode.DIY_OPTIONS_NOT_FOUND);
        }
    }

    public static class CFormNotFoundException extends CEntityNotFoundException {
        public CFormNotFoundException() {
            super(ErrorCode.FORM_NOT_FOUND);
        }
    }

    public static class CSchoolNotFoundException extends CEntityNotFoundException {
        public CSchoolNotFoundException() {
            super(ErrorCode.SCHOOL_NOT_FOUND);
        }
    }

    public static class CStudyNotFoundException extends CEntityNotFoundException {
        public CStudyNotFoundException() {
            super(ErrorCode.STUDY_NOT_FOUND);
        }
    }

    public static class CResumeNotFoundException extends CEntityNotFoundException {
        public CResumeNotFoundException() {
            super(ErrorCode.RESUME_NOT_FOUND);
        }
    }

    public static class CVisaNotFoundException extends CEntityNotFoundException {
        public CVisaNotFoundException() {
            super(ErrorCode.VISA_NOT_FOUND);
        }
    }

    public static class CTourNotFoundException extends CEntityNotFoundException {
        public CTourNotFoundException() {
            super(ErrorCode.TOUR_NOT_FOUND);
        }
    }

    public static class CBoardNotFoundException extends CEntityNotFoundException {
        public CBoardNotFoundException() {
            super(ErrorCode.BOARD_NOT_FOUND);
        }
    }

    public static class CTripNotFoundException extends CEntityNotFoundException {
        public CTripNotFoundException() {
            super(ErrorCode.TRIP_NOT_FOUND);
        }
    }
}
