import { ProgressBar } from "../sharedComponents/progressBar/progressBar";
import {
  useGetInfosFromItemList,
  useGetInfosFromPurchasedItemsList,
} from "../../utils/hooks/customHooks";
export const SingleListStats = ({ listID }) => {
  const {
    result: itemListLength,
    loading: itemListLoading,
    error: itemListError,
  } = useGetInfosFromItemList({ listID, operationType: "len" });

  const {
    result: purchasedItemListLength,
    loading: purchasedItemListLoading,
    error: purchasedItemListError,
  } = useGetInfosFromPurchasedItemsList({ listID, operationType: "len" });

  if (itemListLoading || purchasedItemListLoading) return <div>Loading...</div>;
  if (itemListError || purchasedItemListError) return <div>Error occurred</div>;
  const procent = (purchasedItemListLength / (itemListLength+purchasedItemListLength)) * 100;
  console.log( typeof procent,procent);
  if(isNaN(procent)) return <ProgressBar progress={0} color="var(--secondary-color)" />;
  if (procent === 0)
    return <ProgressBar progress={0} color="var(--secondary-color)" />;

  return (
    <ProgressBar progress={procent.toFixed(1)} color="var(--secondary-color)" />
  );
};
