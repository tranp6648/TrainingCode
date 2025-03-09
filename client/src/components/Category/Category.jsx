import Layout from "../Layout/Layout";
import {use, useEffect, useState} from "react";
import 'react-quill/dist/quill.snow.css';
import {add, get, remove, update} from "../Services/categoryApi";
import Pagination from 'react-paginate';
import 'react-paginate/theme/basic/react-paginate.css';
import Swal from "sweetalert2";

function Category(){
    const[FromData,setFromData]=useState({
        name: '',
        content: '',
        id:'',
        updateName:'',
        updateContent:'',
    });
    const[currentPage,setCurrentPage]=useState(0);
    const [perPage,setPerPage]=useState(5);
    const [searchTerm, setSearchtem] = useState('');
    const[IsClosingPopup,setIsClosingPopup]=useState(false);
    const[isPopupVisible,setIsPopupVisible]=useState(false);
    const closingAnimation = {
        animation: 'flipright 0.5s',
    };
    const popupContentStyle = {
        background: 'white',
        padding: '20px',
        maxWidth: '400px',
        textAlign: 'center',
        borderRadius: '8px',
        boxShadow: '0 0 10px rgba(0, 0, 0, 0.1)',
        animation: 'flipleft 0.5s', // Default animation
    };
    const[Categories,setCategories]=useState([]);
    const fetchData = async () => {
        try {
            const tokentoken = sessionStorage.getItem('token');
            const data=await get(tokentoken);
            setCategories(data.data)
        }catch (error){
            console.error("Error getting categories:", error);
        }
    }
    useEffect(()=>{

        fetchData();
    },[]);
    const handleUpdate=async (e)=>{
        e.preventDefault();
        try {
            const tokentoken = sessionStorage.getItem('token');
            const response=await update(FromData.id,FromData,tokentoken);
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
            setIsPopupVisible(false);
            setFromData({
                name: '',
                content: '',
                id:'',
                updateName:'',
                updateContent:'',
            })
            fetchData();
        }catch (error){
            Swal.fire({
                icon: 'error',
                title: error.message,
                showConfirmButton: false,
                timer: 1500,
            })

        }
    }
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
    const filterCategories=Categories.filter(categories=>
    categories.name.toLowerCase().includes(searchTerm.toLowerCase())
    );
    const indexOflastCategories=(currentPage+1)*perPage;
    const indexofFirstCategories=indexOflastCategories-perPage;
    const currentCategories=filterCategories.slice(indexofFirstCategories,indexOflastCategories);
    const handlePageclick = (data) => {
        setCurrentPage(data.selected);
    };
    const handleEditClick=(id)=>{

        const selectedCategories=Categories.find(category=>category.id === id);
        console.log(selectedCategories);
        if(selectedCategories){
            FromData.id=selectedCategories.id;
            FromData.updateName=selectedCategories.name;
            FromData.updateContent=selectedCategories.description;
            setIsPopupVisible(true);
        }
    }
    const handleClosePopup=()=>{
        setIsClosingPopup(true);
        setTimeout(()=>{
            FromData.id='';
            FromData.updateName='';
            FromData.updateContent='';
            setIsPopupVisible(false);
            setIsClosingPopup(false);
        },500)
    }
    const deleteSubmit=async (id)=>{
        try {
            const confirmation = await Swal.fire({
                title: 'Are you sure?',
                text: 'You won\'t be able to revert this!',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, delete it!',
            });
            if(confirmation.isConfirmed){
                const tokentoken = sessionStorage.getItem('token');
                const response=await remove(id,tokentoken);
                if(response.status === 200){
                    Swal.fire({
                        icon: 'success',
                        title: response.message,
                        showConfirmButton: false,
                        timer: 1500,
                    })
                    fetchData();
                }else{
                    Swal.fire({
                        icon: 'error',
                        title: response.message,
                        showConfirmButton: false,
                    })
                }
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
                            <div className="d-flex align-items-center mb-2 ms-2">
                                <label htmlFor="search" className="text-secondary me-2">Search</label>
                                <input
                                    type="text"
                                    id="search"
                                    name="search"
                                    value={searchTerm}
                                    onChange={(e)=>setSearchtem(e.target.value)}
                                    placeholder="Enter your search term"
                                    className="form-control w-auto"
                                />
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
                                    {currentCategories.map((item, index) => (
                                        <tr key={index}>
                                            <td>{++index}</td>
                                            <td>{item.name}</td>
                                            <td><button className='btn btn-primary fw-bold px-4 py-2' onClick={()=>handleEditClick(item.id)}>Edit</button></td>
                                            <td><button className="btn btn-danger fw-bold px-4 py-2" onClick={()=>deleteSubmit(item.id)}>Remove</button></td>
                                        </tr>
                                    ))}

                                    </tbody>

                                </table>
                                <Pagination
                                    previousLabel={'previous'}
                                    nextLabel={'next'}
                                    breakLabel={'...'}
                                    pageCount={Math.ceil(filterCategories.length / perPage)}
                                    marginPagesDisplayed={2}
                                    pageRangeDisplayed={5}
                                    onPageChange={handlePageclick}
                                    containerClassName={'pagination'}
                                    activeClassName={'active'}
                                    previousClassName={'page-item'}
                                    previousLinkClassName={'page-link'}
                                    nextClassName={'page-item'}
                                    nextLinkClassName={'page-link'}
                                    breakClassName={'page-item'}
                                    breakLinkClassName={'page-link'}
                                    pageClassName={'page-item'}
                                    pageLinkClassName={'page-link'}

                                />


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
            {isPopupVisible && (
                <div className="popup-container">

                    <div className="popup-content" style={IsClosingPopup ? { ...popupContentStyle, ...closingAnimation } : popupContentStyle}>
                        <div className='flex justify-end' style={{display:'flex',justifyContent:'flex-end'}}>
                            <button onClick={handleClosePopup} className="btn btn-danger fw-bold float-end">
                                <i className="fas fa-times"></i>
                            </button>

                        </div>

                        <div >


                        </div>
                        <form onSubmit={handleUpdate} role="form" >
                            <div className="box-body">
                                {/* Form fields go here */}
                                <div className="form-group">
                                    <label className='float-left'>Name</label>
                                    <input name='UpdateNameCategory' className="form-control" value={FromData.updateName} onChange={(e) => setFromData({ ...FromData, updateName: e.target.value })} />

                                </div>



                                <div className="form-group">
                                    <label className='float-left'>Description</label>
                                    <br />
                                    <textarea className="form-control" id="exampleInputEmail2" value={FromData.updateContent}
                                              onChange={(e)=>setFromData({...FromData,updateContent: e.target.value})}></textarea>

                                </div>

                            </div>

                            <div className="box-footer">
                                <button type="submit" className="btn btn-primary">
                                    Update
                                </button>
                            </div>
                        </form>


                    </div>
                </div>
            )}
        </Layout>

    </>
)
}

export default Category;