import {api} from "../Url/api";

const BaseUrl = api();
export const remove=async (id,token)=>{
    try {
        const response=await fetch(`${BaseUrl}categories/${id}`,{
            method:'DELETE',
            headers:{
                'Authorization': 'Bearer ' + token,
                'Content-Type': 'application/json'
            }
        });
        return await response.json();
    }catch (error) {
        console.log(error);
    }
}
export const update=async (id,categoryDto,token)=>{
    try {
        const response=await fetch(`${BaseUrl}categories/${id}`,{
            method:'PUT',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json', // Specify JSON content
            },
            body:JSON.stringify({name:categoryDto.updateName,description:categoryDto.updateContent}),
        });


        // Return the response as JSON if the request is successful
        return await response.json();
    }catch (error){
    console.log(error);
    }
}
export const add = async (categoryDto, token) => {
    try {
        const response = await fetch(`${BaseUrl}categories`, {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json', // Specify JSON content
            },
            body: JSON.stringify({name:categoryDto.name,description:categoryDto.content}) // Convert categoryDto to a JSON string
        });

        if (!response.ok) {
            // Handle the error case if the response is not successful
            const errorResponse = await response.json();
            console.error("Error:", errorResponse);
            return null;
        }

        // Return the response as JSON if the request is successful
        return await response.json();
    } catch (error) {
        console.error("Error fetching categories:", error);
        return null;
    }
};
export const get=async (token) => {
    try {
        const response = await fetch(`${BaseUrl}categories`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            }
        })

        if (!response.ok) {
            // Handle the error case if the response is not successful
            const errorResponse = await response.json();
            console.error("Error:", errorResponse);
            return null;
        }

        // Return the response as JSON if the request is successful
        return await response.json();
    }catch (error) {
        console.error("Error getting categories:", error);
    }
}
