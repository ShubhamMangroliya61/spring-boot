import AddTemp from "./components/Temp/AddTemp";
import DeleteTemp from "./components/Temp/DeleteTemp";
import GetAllTemp from "./components/Temp/GetAllTemp";
import UpdateTemp from "./components/Temp/UpdateTemp";

function App() {
  return (
    <div className="h-screen w-screen bg-black">
      <div className="container w-[70%] m-auto align-middle text-center">
        <GetAllTemp />
        <DeleteTemp />
        <AddTemp />
        <UpdateTemp />
      </div>
    </div>
  );
}

export default App;
