import { AxiosResponse } from "axios";
import axios from "../config/http";
import MeetUpUserSearchForm from "../model/forms/MeetUpUserSearchForm";
import MeetUpUser from "../model/MeetUpUser";

export const meetUpUserService = {
    search: async (payload: MeetUpUserSearchForm): Promise<AxiosResponse<MeetUpUser[]>> => {
        return axios.get("/users?query=" + payload.query);
    }
}

export default meetUpUserService;