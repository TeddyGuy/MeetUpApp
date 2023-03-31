import { AxiosResponse } from "axios";
import axios from "../config/http";
import MeetUpEventSearchForm from "../model/forms/MeetUpEventSearchForm";
import { MeetUpEvent } from "../model/MeetUpEvent";
import { MeetUpEventSaveRequest } from "../model/payload/request/MeetUpEventSaveRequest";

export const meetUpEventService = {
    save: async (payload: MeetUpEventSaveRequest) => {
        const header: string = "Bearer " + localStorage.getItem("jwt");
        return axios.post("/events", payload, {headers: {Authorization: header }});
    },

    join: async (eventId: number) => {
        const header: string = "Bearer " + localStorage.getItem("jwt");
        return axios.post("/events/" + eventId + "/join",{},{headers : {Authorization: header }})
    },

    search: async (payload?: MeetUpEventSearchForm): Promise<AxiosResponse<MeetUpEvent[]>> => {
        if(payload){
            return axios.get("/events?query=" + payload.query);
        }
        return axios.get("/events");
    }
};

export default meetUpEventService;