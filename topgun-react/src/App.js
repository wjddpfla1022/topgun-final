import './App.css';
import Test from './components/Test';
import MainPage from './MainPage/MainPage';
import { HashRouter } from 'react-router-dom';
import MainContent from './components/search/MainContent';
const App = () => {
  return (
    <>
      <HashRouter>
        {/* <MainPage /> */}
        <Test />
        {/* <MainContent /> */}
      </HashRouter>
    </>
  );
}

export default App;
