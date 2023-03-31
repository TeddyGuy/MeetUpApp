import { createContext } from "react";
import DecodedJwt from "../model/AuthenticatedUser";
import jwt_decode from "jwt-decode";

export const defaultDecodedJwtValue = (): DecodedJwt | undefined => {
    const jwt = localStorage.getItem("jwt");
    if(jwt == null) return undefined;
    const decodedJwt: DecodedJwt = jwt_decode(jwt);
    if(decodedJwt.exp < Date.now()) return decodedJwt;
    return undefined;
}

export interface AuthContextValues {
    decodedJwt: DecodedJwt | undefined;
    setDecodedJwt: React.Dispatch<React.SetStateAction<DecodedJwt | undefined>> | undefined ; 
}

export const AuthContext = createContext<AuthContextValues>({
    decodedJwt: undefined,
    setDecodedJwt: undefined
});
