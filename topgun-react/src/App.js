import Footer from './components/Footer/Footer';
import Header from './components/Header/Header';
import MainPage from './components/MainPage/MainPage';
import Login from './components/Login/Login';
import NotFound from './components/NotFound/NotFound';
import './components/Global.css';
import Test from './components/Test';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.js';
import 'react-datepicker/dist/react-datepicker.css'; // 스타일 가져오기
import MainContent from './components/search/MainContent';
import { Route, Routes, useLocation } from 'react-router-dom';



const App = () => {
  const location = useLocation();

  // 헤더를 숨길 경로 배열
  const noHeaderRoutes = ['/login','/join'];

  return (
    <>
      {!noHeaderRoutes.includes(location.pathname) && <Header />}
      <Routes>
        <Route path="/" element={<MainPage />} />
        <Route path="/login" element={<Login />} /> {/* 로그인 */}
        <Route path="/test" element={<Test />} />
        <Route path="/main-content" element={<MainContent />} />
        <Route path="*" element={<NotFound />} /> {/* 모든 잘못된 경로 처리 */}
        
      </Routes>
      <Footer />
    </>
  );
}

export default App;
