import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { Alert, AlertDescription, AlertTitle } from "@/components/ui/alert";
import { CheckCircle2 } from "lucide-react";
const Success = () => {
  const navigate = useNavigate();
  useEffect(() => {
    const timer = setTimeout(() => {
      navigate("/trips");
    }, 3000);
    return () => clearTimeout(timer);
  }, [navigate]);
  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100">
      <Alert className="w-full max-w-md">
        <CheckCircle2 className="h-4 w-4" />
        <AlertTitle>Success!</AlertTitle>
        <AlertDescription>
          Your payment was successful. You will be redirected to the trips page shortly.
        </AlertDescription>
      </Alert>
    </div>
  );
};
export default Success;