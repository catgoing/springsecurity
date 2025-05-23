package com.example.common;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonPropertyOrder({"meta", "data"})
public class CommonResponse<T> {
	

    @Schema(description = "메타")
    private final Meta meta;

    @Schema(description = "데이터")
    private final T data;

    /**
     * 생성자
     * 
     * @param meta 메타
     * @param data 데이터
     */
    private CommonResponse(Meta meta, T data) {
        this.meta = meta;
        this.data = data;
    }

    /**
     * API 요청이 성공했음을 의미하는 null 응답결과를 생성하여 반환한다.
     * 
     * @param data 데이터
     * @return API 응답 결과
     */
    public static final CommonResponse<Object> ok() {
        return new CommonResponse<>(new Meta("0000", "성공"), null);
    }

    /**
     * API 요청이 성공했음을 의미하는 응답결과를 생성하여 반환한다.
     * 
     * @param data 데이터
     * @return API 응답 결과
     */
    public static final <T> CommonResponse<T> ok(T data) {
        return new CommonResponse<>(new Meta("0000", "성공"), data);
    }

    public static final <T> CommonResponse<T> fail(String code, String msg) {
        return new CommonResponse<>(new Meta(code, msg), null);
    }

    /**
     * API 요청이 실패했음을 의미하는 응답결과를 생성하여 리턴한다.
     * 
     * @param code API 응답 코드
     * @param msg API 응답 메시지
     * @param data 데이터
     * @return API 응답 결과
     */
    public static final <T> CommonResponse<T> fail(String code, String msg, T data) {
        return new CommonResponse<>(new Meta(code, msg), data);
    }

    /**
     * 메타를 반환한다.
     * 
     * @return API 메타
     */
    public Meta getMeta() {
        return meta;
    }

    /**
     * 데이터를 반환한다.
     * 
     * @return 데이터
     */
    public T getData() {
        return data;
    }

    /**
     * 메타
     */
    @JsonPropertyOrder({"code", "msg"})
    public static class Meta {

        @Schema(description = "API 응답 코드")
        private final String code;

        @Schema(description = "API 응답 메시지")
        private final String msg;

        /**
         * 생성자
         * 
         * @param code API 응답 코드
         * @param msg API 응답 메시지
         */
        private Meta(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        /**
         * API 응답 코드를 반환한다.
         * 
         * @return API 응답 코드
         */
        public String getCode() {
            return code;
        }

        /**
         * API 응답 메시지를 반환한다.
         * 
         * @return API 응답 메시지
         */
        public String getMsg() {
            return msg;
        }
        
    }

}
