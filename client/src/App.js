import './App.css';
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import Login from "./components/Login/login";
import Home from "./components/Home/Home";
import Category from "./components/Category/Category";

import Register from "./components/register/register";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Login/>}/>
                <Route path="/Home" element={<Home/>}/>
                <Route path="/Category" element={<Category/>}/>
                <Route path="/Register" element={<Register/>}/>
            </Routes>
        </Router>
    );
}

export default App;
