import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { Alert, AlertDescription, AlertTitle } from "@/components/ui/alert";
import { XCircle } from "lucide-react";
const Fail = () => {
  const navigate = useNavigate();
  useEffect(() => {
    const timer = setTimeout(() => {
      navigate("/trips");
    }, 3000);
    return () => clearTimeout(timer);
  }, [navigate]);
  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100">
      <Alert variant="destructive" className="w-full max-w-md">
        <XCircle className="h-4 w-4" />
        <AlertTitle>Payment Failed</AlertTitle>
        <AlertDescription>
          Your payment was unsuccessful. You will be redirected to the trips page shortly.
        </AlertDescription>
      </Alert>
    </div>
  );
};
export default Fail;