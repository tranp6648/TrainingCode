import { useState } from "react";
import '../../bootstrap/css/bootstrap.min.css';

import '../../dist/css/AdminLTE.min.css';
import '../../plugins/iCheck/square/blue.css';
import {register} from "../Services/authService";
import Swal from "sweetalert2";
function Register() {
    const [username, setUsername] = useState("");
    const[password, setPassword] = useState("");
        const handleRegister=async (e)=>{
            e.preventDefault();
            try {

                const data=await register(username,password,"ROLE_ADMIN");
                console.log(data);
                if(data!=null){
                    Swal.fire({
                        title: data.message,
                        icon: 'success',
                    })
                   setUsername("");
                    setPassword("");
                }else{
                    Swal.fire({
                        icon: "error",
                        title:data.message,
                    })
                    setUsername("");
                    setPassword("");
                }
            }catch(err){
                console.log(err);
            }
        }
    return(
        <div style={{paddingBottom:'10vh',paddingTop:'18vh'}} className="register-page" >
            <div className="register-box">
                <div className="register-logo">
                    <a href="../../index2.html"><b>Admin</b>LTE</a>
                </div>

                <div className="register-box-body">
                    <p className="login-box-msg">Register a new membership</p>
                    <form onSubmit={handleRegister} method="post">
                        <div className="form-group has-feedback">
                            <input type="text" className="form-control" value={username} onChange={(e)=>setUsername(e.target.value)} placeholder="User Name"/>
                            <span className="glyphicon glyphicon-user form-control-feedback"></span>
                        </div>
                        <div className="form-group has-feedback">
                            <input type="password" className="form-control" value={password} onChange={(e)=>setPassword(e.target.value)} placeholder="Password"/>

                        </div>

                        <div className="row">
                            <div className="col-xs-8">
                                <div className="checkbox icheck">

                                </div>
                            </div>

                            <div className="col-xs-4">
                                <button type="submit" className="btn btn-primary btn-block btn-flat">Register</button>
                            </div>

                        </div>
                    </form>



                    <a href="login.html" className="text-center">I already have a membership</a>
                </div>

            </div>
        </div>
    )
}
export default Register;