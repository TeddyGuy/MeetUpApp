import { useContext } from "react";
import axios from "../config/http";
import { AuthContext, AuthContextValues } from "../contexts/AuthContext";
import DecodedJwt from "../model/AuthenticatedUser";
import SignInRequest from "../model/payload/request/SignInRequest";
import SignUpRequest from "../model/payload/request/SignUpRequest";

export const authService = {

    signIn: async (payload: SignInRequest) => {
        return axios.post("/auth/sign-in", payload);
    },

    signUp: async (payload: SignUpRequest) => {
        return axios.post("/auth/sign-up", payload);
    },

    signOut: async (setDecodedJwt: React.Dispatch<React.SetStateAction<DecodedJwt | undefined>> | undefined) => {
        localStorage.removeItem("jwt");
        setDecodedJwt?.(undefined);
    }
}

export default authService;
