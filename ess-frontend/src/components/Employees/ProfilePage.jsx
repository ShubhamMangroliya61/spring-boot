import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { useGlobalContext } from "../../context/appContext";

function ProfilePage() {
  const { authFetch } = useGlobalContext();
  const { userId } = useParams();
  const [profileData, setProfileData] = useState(null);

  useEffect(() => {
    authFetch
      .get(`/employee/${userId}`)
      .then((response) => setProfileData(response.data))
      .catch((error) => console.error("Error fetching profile data:", error));
  }, [authFetch, userId]);

  return (
    <div className="profile-page min-h-screen flex items-center justify-center bg-gray-900 text-white">
    {profileData ? (
      <div className="text-center p-6 bg-gray-800 rounded-md shadow-md">
        <h1 className="text-3xl font-bold mb-4">{profileData.firstName}</h1> - <h1 className="text-3xl font-bold mb-4">{profileData.lastName}</h1> 
        <p className="text-lg mb-2">Email: {profileData.email}</p>
      </div>
    ) : (
      <p className="text-lg">Loading profile...</p>
    )}
  </div>
  );
}

export default ProfilePage;
