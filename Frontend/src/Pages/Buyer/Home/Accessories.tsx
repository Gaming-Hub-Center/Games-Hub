import React, { useState } from "react";
import { Container, Row, Col, Pagination } from "react-bootstrap";
import { NavbarC } from "../../../Components/NavbarC";
import { ProductCard } from "../../../Components/ProductCard";
import "./PaginationC.css";

export function HomeAccessories() {
  const productCardPropsList = [
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Michael is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Michael is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Michael is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Michael is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Michael is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Michael is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Michael is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Michael is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Michael is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Michael is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Michael is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Michael is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Michael is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Michael is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Michael is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Michael is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Michael is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Michael is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Michael is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Michael is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Michael is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Michael is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Michael is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Michael is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Michael is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Michael is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Michael is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Michael is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Michael is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Michael is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Michael is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Michael is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Michael is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)Michael is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Michael is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Michael is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Michael is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description:
        "Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA V",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/logo4.png"],
    },
    {
      title: "GTA VI",
      description: "Trevor is gonna kill u bardo:)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
    {
      title: "GTA IV",
      description: "Trevor is gonna kill u :)",
      images: ["/src/data/GTAV.jpg", "/src/data/logo4.png"],
    },
  ];
  const itemsPerPage = 20;
  const maxVisiblePages = 5;
  const [currentPage, setCurrentPage] = useState(1);

  // Calculate the index range for the current page
  const startIndex = (currentPage - 1) * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;

  // Get the product cards for the current page
  const currentProductCards = productCardPropsList.slice(startIndex, endIndex);

  const totalPages = Math.ceil(productCardPropsList.length / itemsPerPage);

  const handlePageChange = (page) => {
    setCurrentPage(page);
  };

  const renderPaginationItems = () => {
    const items = [];
    const startPage = Math.max(
      1,
      currentPage - Math.floor(maxVisiblePages / 2)
    );
    const endPage = Math.min(totalPages, startPage + maxVisiblePages - 1);

    for (let i = startPage; i <= endPage; i++) {
      items.push(
        <Pagination.Item
          key={i}
          active={i === currentPage}
          onClick={() => handlePageChange(i)}
        >
          {i}
        </Pagination.Item>
      );
    }

    return items;
  };

  return (
    <>
      <Container
        fluid
        style={{
          height: "170vh",
          padding: 0,
          backgroundColor: "#121212",
        }}
      >
        <NavbarC></NavbarC>
        <Row style={{ display: "flex", flexDirection: "row", height: "160vh" }}>
          <Col
            md={2}
            style={{
              height: "77vh",
              marginTop: "30px",
              marginLeft: "55px",
              backgroundColor: "white",
            }}
          ></Col>
          <Col
            style={{
              height: "82vh",
              marginTop: "30px",
              display: "flex",
              flexDirection: "row",
              flexWrap: "wrap",
              justifyContent: "flex-start",
            }}
          >
            {currentProductCards.map((productCardProps, index) => (
              <ProductCard key={startIndex + index} {...productCardProps} />
            ))}
          </Col>
        </Row>
        <Row style={{ height: "8vh", backgroundColor: "black" }}>
          <Col
            style={{
              height: "8vh",
              display: "flex",
              justifyContent: "center",
            }}
          >
            <Pagination style={{ marginTop: "20px", color: "black" }}>
              <Pagination.First onClick={() => handlePageChange(1)} />
              <Pagination.Prev
                onClick={() =>
                  handlePageChange(currentPage > 1 ? currentPage - 1 : 1)
                }
              />
              {renderPaginationItems()}
              <Pagination.Next
                onClick={() =>
                  handlePageChange(
                    currentPage < totalPages ? currentPage + 1 : totalPages
                  )
                }
              />
              <Pagination.Last onClick={() => handlePageChange(totalPages)} />
            </Pagination>
          </Col>
        </Row>
      </Container>
    </>
  );
}
