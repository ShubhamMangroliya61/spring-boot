import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { useGlobalContext } from "../context/appContext";

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
    <div className="profile-page">
      {profileData ? (
        <>
          <h1>{profileData.name}</h1>
          <p>Email: {profileData.email}</p>
          <p>Position: {profileData.position}</p>
        </>
      ) : (
        <p>Loading profile...</p>
      )}
    </div>
  );
}

export default ProfilePage;
