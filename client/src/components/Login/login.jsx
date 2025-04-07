import React, { useState } from "react";
import '../../bootstrap/css/bootstrap.min.css';
import '../../plugins/iCheck/square/blue.css';
import '../../dist/css/AdminLTE.min.css';
import {login} from "../Services/authService";
import Swal from "sweetalert2";
import {useNavigate} from "react-router-dom";
function Login() {
    const navigate = useNavigate();
    const [username, setUsername] = useState("");
    const[password, setPassword] = useState("");
    const handleLogin=async (e)=>{
        e.preventDefault();
        try {
            const data=await login(username,password);
            if(data!=null){
                Swal.fire({
                    icon:"success",
                    title:'Login Success!',
                }).then(()=>{

                    sessionStorage.setItem("token", data.data);
                    navigate("/Home");
                });
            }else{
                Swal.fire({
                    icon:"error",
                    title:'Login Failed',
                })

            }
        }catch(error){
            console.log(error);
        }
    }
    return (
        <div style={{paddingBottom:'10vh',paddingTop:'18vh'}} className='login-page'>
            <div className="login-box">
                <div className="login-logo">
                    <a href="../../index2.html"><b>Admin</b>LTE</a>
                </div>
                <div className="login-box-body">
                    <p className="login-box-msg">Sign in to start your session</p>
                    <form onSubmit={handleLogin} >
                        <div className="form-group has-feedback">
                            <input type="text" value={username} onChange={(e)=>setUsername(e.target.value)} className="form-control" placeholder="Username"/>
                            <span className="glyphicon glyphicon-envelope form-control-feedback"></span>
                        </div>
                        <div className="form-group has-feedback">
                            <input type="password" value={password} onChange={(e)=>setPassword(e.target.value)} className="form-control" placeholder="Password"/>
                            <span className="glyphicon glyphicon-lock form-control-feedback"></span>
                        </div>
                        <div className="row">


                            <div className="col-xs-4">
                                <button type="submit" className="btn btn-primary btn-block btn-flat">Sign In</button>
                            </div>

                        </div>
                    </form>





                    <a href="register.html" className="text-center">Register a new membership</a>

                </div>

            </div>
        </div>

    )
}

export default Login;