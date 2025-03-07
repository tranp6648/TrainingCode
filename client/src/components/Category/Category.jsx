import Layout from "../Layout/Layout";
import {use, useEffect, useState} from "react";
import 'react-quill/dist/quill.snow.css';
import {add,get} from "../Services/categoryApi";
import Swal from "sweetalert2";

function Category(){
    const[FromData,setFromData]=useState({
        name: '',
        content: ''
    });
    const[Categories,setCategories]=useState([]);
    useEffect(()=>{
        const fetchData = async () => {
            try {
                const tokentoken = sessionStorage.getItem('token');
                const data=await get(tokentoken);
                setCategories(data.data)
            }catch (error){
                console.error("Error getting categories:", error);
            }
        }
        fetchData();
    },[])
    const handleAdd=async (e)=>{
        e.preventDefault();
        try {
            const tokentoken = sessionStorage.getItem('token');


            const response=await add(FromData,tokentoken);
            if(response.status === 200){
                Swal.fire({
                    icon: 'success',
                    title: response.message,
                    showConfirmButton: false,
                    timer: 1500,
                })
                setFromData({
                    name: '',
                    content: ''
                })
            }else{
                Swal.fire({
                    icon: 'error',
                    title: response.message,
                    showConfirmButton: false,
                    timer: 1500,
                })
            }
        }catch (error){
            console.log(error);
        }
    }
return(
    <>
        <Layout>
            <div className="content-wrapper">
                <section className="content-header">
                    <h1>
                        Category

                    </h1>
                    <ol className="breadcrumb">
                        <li><a href="#"><i className="fa fa-dashboard"></i> Home</a></li>
                        <li><a href="#">Category</a></li>
                    </ol>
                </section>
                <section className="content">
                    <div className="row">
                        <div className="box box-primary" style={{maxHeight: 'auto'}}>
                            <div className="box-header">
                                <h3 className="box-title">Category</h3>
                            </div>
                            <form role="form" onSubmit={handleAdd}>
                                <div className="box-body">
                                    {/* Form fields go here */}
                                    <div className="form-group">
                                        <label>Name</label>
                                        <input className="form-control" id="exampleInputEmail1"
                                               placeholder="Enter Name Category" value={FromData.name}
                                               onChange={(e)=>setFromData({...FromData,name: e.target.value})}
                                        />

                                    </div>

                                    <div className="form-group">
                                        <label >Content</label>
                                       <textarea className="form-control" id="exampleInputEmail2" value={FromData.content}
                                       onChange={(e)=>setFromData({...FromData,content: e.target.value})}></textarea>


                                    </div>


                                </div>

                                <div className="box-footer">
                                    <button type="submit" className="btn btn-primary">
                                        Submit
                                    </button>
                                </div>
                            </form>
                        </div>
                        <div className="box">
                            <div className="box-header">
                                <h3 className="box-title">List Categories</h3>
                            </div>


                            <div className="box-body">
                                <table id="example1" className="table table-bordered table-striped">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Name</th>
                                        <th>Edit</th>
                                        <th>Remove</th>
                                    </tr>

                                    </thead>
                                    <tbody>
                                    {Categories.map((item,index) => (
                                        <tr key={index}>
                                            <td>{++index}</td>
                                            <td>{item.name}</td>
                                        </tr>
                                    ))}

                                    </tbody>

                                </table>


                            </div>
                        </div>
                        {/* Additional boxes go here */}
                    </div>
                </section>
            </div>

            <footer className="main-footer">
                <div className="pull-right hidden-xs">
                    <b>Version</b> 2.0
                </div>
                <strong>Copyright &copy; 2014-2015 <a href="http://almsaeedstudio.com">Almsaeed Studio</a>.</strong> All
                rights reserved.
            </footer>
        </Layout>

    </>
)
}

export default Category;