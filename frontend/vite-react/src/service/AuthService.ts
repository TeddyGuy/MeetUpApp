import axios from "../config/http";
import SignInRequest from "../model/payload/request/SignInRequest";
import SignUpRequest from "../model/payload/request/SignUpRequest";

export const authService = {
    signIn: async (request: SignInRequest) => {
        return axios.post("/auth/sign-in", request);
    },

    signUp: async (request: SignUpRequest) => {
        return axios.post("/auth/sign-up", request);
    },
}

export default authService;
