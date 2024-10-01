import React, { useEffect, useState } from 'react';

const Test = () => {
  const [exchangeRates, setExchangeRates] = useState({ inr: null, krw: null });
  const [weather, setWeather] = useState(null);
  const [currencies, setCurrencies] = useState([]); // 통화 목록 상태 추가
  const fromCurrency = 'usd';

  const getExchangeRates = async () => {
    const url = `https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@latest/v1/currencies/${fromCurrency}.json`;
    try {
      const response = await fetch(url);
      const data = await response.json();
      if (data[fromCurrency]) {
        setExchangeRates({
          inr: data[fromCurrency]['inr'],
          krw: data[fromCurrency]['krw'],
        });
      } else {
        console.error("환율 데이터가 없습니다.");
      }
    } catch (error) {
      console.error("환율 조회 중 오류 발생:", error);
    }
  };

  const getWeather = async () => {
    const latitude = 37.5665; // 서울 위도
    const longitude = 126.978; // 서울 경도
    const url = `https://api.open-meteo.com/v1/forecast?latitude=${latitude}&longitude=${longitude}&current_weather=true`;

    try {
      const response = await fetch(url);
      const data = await response.json();
      if (data.current_weather) {
        setWeather(data.current_weather);
      } else {
        console.error("날씨 데이터가 없습니다.");
      }
    } catch (error) {
      console.error("날씨 조회 중 오류 발생:", error);
    }
  };

  const getCurrencies = async () => {
    const url = 'https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@latest/v1/currencies.json';
    try {
      const response = await fetch(url);
      const data = await response.json();
      setCurrencies(data); // 통화 목록 상태 설정
    } catch (error) {
      console.error("통화 목록을 가져오는 중 오류 발생:", error);
    }
  };

  useEffect(() => {
    getExchangeRates();
    getWeather();
    getCurrencies(); // 통화 목록 가져오기 호출
  }, []);

  return (
    <div>
      {exchangeRates.inr && exchangeRates.krw ? (
        <>
          <h1>{`1 ${fromCurrency.toUpperCase()}는 ${exchangeRates.inr} INR입니다.`}</h1>
          <h1>{`1 ${fromCurrency.toUpperCase()}는 ${exchangeRates.krw} KRW입니다.`}</h1>
        </>
      ) : (
        <p>환율을 가져오는 중입니다...</p>
      )}

      {weather ? (
        <>
          <h2>{`현재 서울의 기온은 ${weather.temperature}°C입니다.`}</h2>
          <p>{`측정 시간: ${new Date(weather.time).toLocaleString()} (UTC)`}</p>
        </>
      ) : (
        <p>날씨를 가져오는 중입니다...</p>
      )}

      <h1>모든 통화 목록</h1>
      <ul>
        {Object.entries(currencies).map(([code, name]) => (
          <li key={code}>{`${code}: ${name}`}</li>
        ))}
      </ul>
    </div>
  );
};

export default Test;
