import './App.css';
import Test from './components/Test';
import MainPage from './MainPage/MainPage';
import { BrowserRouter, HashRouter } from 'react-router-dom';
import MainContent from './components/search/MainContent';
import Flight from './components/Flight';
const App = () => {
  return (
    <>
        {/* <MainPage /> */}
        <Test />
        {/* <MainContent /> */}
        <Flight/>
    </>
  );
}

export default App;
