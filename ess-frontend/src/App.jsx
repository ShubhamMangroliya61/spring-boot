import Login from "./components/Login";
import UpdateTemp from "./components/Temp/UpdateTemp.jsx";

function App() {
  return (
    // <TempCRUD />
    <>
      <Login />
      <div className="fixed">
        <UpdateTemp />
      </div>
    </>
  );
}

export default App;
