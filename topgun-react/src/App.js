import './App.css';
import Test from './components/Test';
import MainPage from './MainPage/MainPage';
import { BrowserRouter, HashRouter } from 'react-router-dom';
import MainContent from './components/search/MainContent';
import { RecoilRoot } from 'recoil';
const App = () => {
  return (
    <>
      <RecoilRoot>
      <BrowserRouter>
        {/* <MainPage /> */}
        <Test />
        {/* <MainContent /> */}
      </BrowserRouter>
      </RecoilRoot>
    </>
  );
}

export default App;
