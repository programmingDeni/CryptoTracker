interface NavbarUIProps {
  viewName?: string;
}

export default function NavbarUI({ viewName }: NavbarUIProps) {
  let fallbackViewName = "Übersicht";
  const displayViewName = viewName ?? fallbackViewName;

  return (
    <nav
      style={{
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        padding: "1rem 0",
        background: "#1976d2",
        color: "#fff",
        width: "100vw",
        position: "relative",
      }}
    >
      <span
        className="navbar-logo"
        style={{
          display: "none",
          width: "100%",
          textAlign: "center",
        }}
      >
        <img src="/vite.svg" alt="Logo" style={{ height: 32 }} />
      </span>
      <span
        className="navbar-viewname"
        style={{
          fontWeight: "normal",
          fontSize: "1.2rem",
          width: "100%",
          textAlign: "center",
        }}
      >
        {displayViewName}
      </span>
      <style>
        {`
          @media (min-width: 600px) {
            .navbar-logo { display: inline-block !important; }
            .navbar-viewname { display: none !important; }
          }
          @media (max-width: 599px) {
            .navbar-logo { display: none !important; }
            .navbar-viewname { display: inline !important; }
          }
        `}
      </style>
    </nav>
  );
}
