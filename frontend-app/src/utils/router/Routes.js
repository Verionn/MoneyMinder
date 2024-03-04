import { Route, Routes, useLocation } from "react-router";
import ItemsList from "../../components/ItemsList/ItemsList";
const RenderRoutes = ({ routes,lists=false }) => {
  const location = useLocation();
  return (
    <Routes location={location} key={location.pathname}>
      {routes.map((route, index) => (
        <Route key={index} path={route.path} element={route.element} />
      ))}
      {
        lists && <Route path="/shopping-list/:listId" element={<ItemsList />} />
      }
     
    </Routes>
  );
};

export default RenderRoutes;
