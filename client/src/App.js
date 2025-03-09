import './App.css';
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import Login from "./components/Login/login";
import Home from "./components/Home/Home";
import Category from "./components/Category/Category";


function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Login/>}/>
                <Route path="/Home" element={<Home/>}/>
                <Route path="/Category" element={<Category/>}/>
            </Routes>
        </Router>
    );
}

export default App;
