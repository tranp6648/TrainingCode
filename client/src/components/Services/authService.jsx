import {api} from "../Url/api";
import axios from "axios";
const BaseUrl=api();
export const login = async (username, password) => {
    try {
        const response=await axios.post(BaseUrl+'auth/login',{username, password});
        return response.data;
    } catch (error) {
        console.log(error);
        throw error;
    }
}
export const register=async (username, password,role) => {
    try {
        const response=await axios.post(BaseUrl+'auth/register',{username, password, role});
        return response.data;
    }catch (error) {
        console.log(error);
        throw error;
    }
}