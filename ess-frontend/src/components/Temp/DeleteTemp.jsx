import axios from "axios";

function DeleteTemp() {
  const deleteData = () => {
    axios
      .delete("http://192.168.16.54:8080/api/role/3")
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div>
      <button
        onClick={deleteData}
        className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
      >
        Delete role
      </button>
    </div>
  );
}

export default DeleteTemp;
