import { Building2, FileText, Users, CheckCircle } from "lucide-react";
import { useNavigate } from "react-router-dom";

const Display = () => {
  const navigate = useNavigate();

  const features = [
    {
      icon: FileText,
      title: "Easy Complaint Submission",
      description:
        "Students can quickly submit and track their hostel and campus complaints.",
    },
    {
      icon: Users,
      title: "Role-Based Access",
      description:
        "Separate dashboards for students, wardens, and admins with appropriate permissions.",
    },
    {
      icon: CheckCircle,
      title: "Status Tracking",
      description:
        "Real-time status updates and timeline of complaint resolution.",
    },
    {
      icon: Building2,
      title: "Comprehensive Management",
      description:
        "Manage both hostel and campus complaints in one centralized system.",
    },
  ];

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 via-white to-blue-100 text-gray-800">
      {/* Hero Section */}
      <section className="container mx-auto px-6 py-20 text-center">
        <div className="flex justify-center mb-6">
          <div className="p-4 bg-blue-600 rounded-full">
            <Building2 className="h-12 w-12 text-white" />
          </div>
        </div>
        <h1 className="text-4xl sm:text-5xl font-bold tracking-tight leading-tight">
          Campus Complaint Management System
        </h1>
        <p className="text-lg sm:text-xl text-gray-600 mt-4">
          Streamline hostel and campus complaint handling with our efficient,
          role-based management platform.
        </p>

        <div className="flex flex-wrap gap-4 justify-center pt-8">
          <button
            onClick={() => navigate("/auth")}
            className="px-6 py-3 rounded-xl bg-blue-600 hover:bg-blue-700 text-white font-semibold shadow-md transition-all"
          >
            Get Started
          </button>
          <button
            onClick={() => navigate("/auth")}
            className="px-6 py-3 rounded-xl border border-blue-500 text-blue-600 font-semibold hover:bg-blue-50 transition-all"
          >
            Login
          </button>
        </div>
      </section>

      {/* Features Section */}
      <section className="container mx-auto px-6 py-20">
        <div className="text-center mb-14">
          <h2 className="text-3xl sm:text-4xl font-bold mb-4">Key Features</h2>
          <p className="text-gray-600 max-w-2xl mx-auto">
            Everything you need to manage campus complaints efficiently.
          </p>
        </div>

        <div className="grid sm:grid-cols-2 lg:grid-cols-4 gap-8">
          {features.map((feature) => (
            <div
              key={feature.title}
              className="p-6 rounded-2xl border border-gray-200 bg-white/80 backdrop-blur-sm shadow-sm hover:shadow-lg transition-shadow"
            >
              <div className="p-3 bg-blue-100 rounded-xl inline-block mb-3">
                <feature.icon className="h-6 w-6 text-blue-600" />
              </div>
              <h3 className="text-lg font-semibold mb-2">{feature.title}</h3>
              <p className="text-gray-600 text-sm leading-relaxed">
                {feature.description}
              </p>
            </div>
          ))}
        </div>
      </section>

      {/* CTA Section */}
      <section className="container mx-auto px-6 py-20">
        <div className="bg-blue-600 text-white text-center p-12 rounded-2xl shadow-xl">
          <h2 className="text-3xl sm:text-4xl font-bold mb-4">
            Ready to get started?
          </h2>
          <p className="text-lg mb-6 opacity-90">
            Join our platform and experience efficient complaint management.
          </p>
          <button
            onClick={() => navigate("/auth")}
            className="px-6 py-3 rounded-xl border border-white text-white font-semibold hover:bg-white hover:text-blue-600 transition-all"
          >
            Create Account
          </button>
        </div>
      </section>

      {/* Footer */}
      <footer className="text-center text-gray-500 py-8 border-t">
        © {new Date().getFullYear()} Campus Complaint System. All rights
        reserved.
      </footer>
    </div>
  );
};

export default Display;
