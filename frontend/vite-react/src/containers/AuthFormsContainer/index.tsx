import { FC, useEffect, useState } from "react";
import { useRefreshEvents } from "../../hooks/useRefreshEvents";
import SignInFormContainer from "../SignInFormContainer";
import SignUpFormContainer from "../SignUpFormContainer";

export const AuthFormsContainer:FC = ():JSX.Element => {
    const [currentForm, setCurrentForm] = useState<JSX.Element>(<SignInFormContainer/>);
    const [newUser, setNewUser] = useState<boolean>(false);
    const toggleForms = () => {
        switch (newUser) {
            case true:
                setCurrentForm(<SignUpFormContainer/>);
                break;
        
            default:
                setCurrentForm(<SignInFormContainer/>);
                break;
        }
    }
    useRefreshEvents(currentForm, newUser);
    useEffect(() => {
        toggleForms();
    },[newUser]);

    return(
        <div>
            <h1>Bienvenue sur Meet Up App</h1>
            {currentForm}
            <button onClick={() => setNewUser(!newUser)}>
                {newUser ? "Déjà inscrit ?" : "Nouveau ici ?"}
            </button>
        </div>
    );
}