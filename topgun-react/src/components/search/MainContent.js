import {Route, Routes} from "react-router";
import MainPage from '../MainPage/MainPage';
import PageNotFound from '../error/PageNotFound';
import Test from '../Test';
import Flight from '../Flight';
const MainContent=()=>{

    return(<>
    <div className="row pt-3">
        <div className="col-sm-10 offset-sm-1">
            <Routes>
                <Route exact path="/" element={<MainPage/>}/> 
                <Route path="/test" element={<Test/>}/>
                <Route path="/flight" element={<Flight/>}/>
                <Route path="*" element={<PageNotFound/>}/>
            </Routes>
        </div>
    </div>
    </>);
};
export default MainContent;