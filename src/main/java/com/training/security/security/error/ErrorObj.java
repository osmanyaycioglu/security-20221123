package com.training.security.security.error;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ErrorObj {
    private List<ErrorObj> subErrors;
    private String errorDesc;
    private int errorCode;

    public ErrorObj() {
    }

    public ErrorObj(List<ErrorObj> subErrors,
                    String errorDesc,
                    int errorCode) {
        this.subErrors = subErrors;
        this.errorDesc = errorDesc;
        this.errorCode = errorCode;
    }

    public static ErrorObjBuilder builder() {
        return new ErrorObjBuilder();
    }

    public static class ErrorObjBuilder {
        private List<ErrorObj> subErrors;
        private String         errorDesc;
        private int            errorCode;

        ErrorObjBuilder() {
        }

        public ErrorObjBuilder withSubErrors(List<ErrorObj> subErrors) {
            this.subErrors = subErrors;
            return this;
        }

        public ErrorObjBuilder withErrorDesc(String errorDesc) {
            this.errorDesc = errorDesc;
            return this;
        }

        public ErrorObjBuilder withErrorCode(int errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public ErrorObj build() {
            return new ErrorObj(subErrors,
                                errorDesc,
                                errorCode);
        }

        public String toString() {
            return "ErrorObj.ErrorObjBuilder(subErrors="
                   + this.subErrors
                   + ", errorDesc="
                   + this.errorDesc
                   + ", errorCode="
                   + this.errorCode
                   + ")";
        }
    }
}
