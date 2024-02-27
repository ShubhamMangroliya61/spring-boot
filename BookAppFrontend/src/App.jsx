import { useEffect, useState } from "react";
import "./App.css";
import axios from "axios";

function App() {
  const [books, setBooks] = useState([]);

  useEffect(() => {
    axios
      .get(`http://localhost:8080/books`)
      .then((res) => console.log(res.data));
  }, []);

  // useEffect(() => {
  //   console.log(books);
  // }, [books]);

  return <>This is first app on linux</>;
}

export default App;
