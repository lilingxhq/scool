package com.man.common.enums;

public enum TokenState {
    /**
     * 过期
     */
    EXPIRED("EXPIRED"),
    /**
     * 无效(token不合法)
     */
    INVALID("INVALID"),
    /**
     * 有效的
     */
    VALID("VALID");

    private String state;

    TokenState(String state) {
        this.state = state;
    }

    /**
     * 根据状态字符串获取token状态枚举对象
     *
     * @param tokenState 根据状态获取
     * @return 返回枚举状态
     */
    public static TokenState getTokenState(String tokenState) {
        TokenState[] states = TokenState.values();
        TokenState ts = null;
        for (TokenState state : states) {
            if (state.toString().equals(tokenState)) {
                ts = state;
                break;
            }
        }
        return ts;
    }

    @Override
    public String toString() {
        return this.state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
