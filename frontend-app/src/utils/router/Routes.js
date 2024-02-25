import { Route, Routes, useLocation } from "react-router";
import ItemsList from "../../components/ItemsList/ItemsList";
const RenderRoutes = ({ routes }) => {
  const location = useLocation();
  return (
    <Routes location={location} key={location.pathname}>
      {routes.map((route, index) => (
        <Route key={index} path={route.path} element={route.element} />
      ))}
      <Route path="/shopping-list/:listId" element={<ItemsList />} />
    </Routes>
  );
};

export default RenderRoutes;
